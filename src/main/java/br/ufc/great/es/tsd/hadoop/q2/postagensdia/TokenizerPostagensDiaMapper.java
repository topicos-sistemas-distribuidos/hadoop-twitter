package br.ufc.great.es.tsd.hadoop.q2.postagensdia;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerPostagensDiaMapper extends Mapper<Object, Text, Text, Text>{
	private Text word = new Text();
	private Text one = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String linha="";
		linha = value.toString();

		if (linha != null) {
			// Utiliza a funcao split da versao jdk1.4
			String[] tokens = linha.split("\t");

			// separa por espacos em branco o token 1 - comentario
			StringTokenizer itr = new StringTokenizer(tokens[1], " ");
			SimpleDateFormat formato2 = new SimpleDateFormat("dd");

			while (itr.hasMoreTokens()) {
				String myKey = itr.nextToken();

				// verifica se o token contem hashtag
				if (myKey.contains("#")) {
					myKey = myKey.replace("\"", "").replace(".", "");
					String[] tokensAux = myKey.split("#");
					for(int i=0; i<tokensAux.length; i++) {
						if (!tokensAux[i].equals("")){
							// separa o token 5 - data
							String[] tokensData = tokens[7].split(" ");
							// pega apenas o indice 2 - dia - para adicionar ao hashmap
							Date dataFormatada = null;
							try {
								dataFormatada = formato2.parse(tokensData[2]);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							GregorianCalendar calendar = new GregorianCalendar();
							calendar.setTime(dataFormatada);
							int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
							word.set("#"+tokensAux[i]);
							one.set(String.valueOf(dia));
							context.write(word, one);
						}
					}
				}

			}
		}

	}
}