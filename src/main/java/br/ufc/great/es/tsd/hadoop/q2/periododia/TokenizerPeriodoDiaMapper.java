package br.ufc.great.es.tsd.hadoop.q2.periododia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerPeriodoDiaMapper extends Mapper<Object, Text, Text, Text>{
	Date manha;
	Date tarde;
	Date noite;
	SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
	FileInputStream stream; 
	InputStreamReader reader;
	BufferedReader br;

	private Text word = new Text();
	private Text one = new Text();

	private void loadFormattedDates() {
		try {
			manha = formato.parse("06:00:00");
			tarde = formato.parse("12:00:00");
			noite = formato.parse("18:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		loadFormattedDates();	

		String linha="";
		linha = value.toString();

		if (linha != null) {
			//Utiliza a funcao split da versao jdk1.4
			String [] tokens = linha.split("\"");

			//separa por espacos em branco o token 1 - comentario
			StringTokenizer itr = new StringTokenizer(tokens[1], " ");			
			SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm:ss");

			while (itr.hasMoreTokens()) {
				String myKey = itr.nextToken();

				//verifica se o token contem hashtag
				if (myKey.contains("#")) {
					myKey = myKey.replace("\"", "").replace(".", "");
					String[] tokensAux = myKey.split("#");
					for(int i=0; i<tokensAux.length; i++) {
						// separa o token 7 - data
						String[] tokensData = tokens[7].split(" ");

						//pega apenas o indice 3 - HH:mm:ss	- para comparar se e Manha Tarde ou Noite
						Date dataFormatada = null;
						try {
							dataFormatada = formato2.parse(tokensData[3]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (dataFormatada.before(manha) && dataFormatada.after(noite)) {						
							word.set("#"+tokensAux[i]);
							one.set("Noite");
							context.write(word, one);

						} else if (dataFormatada.before(tarde) && dataFormatada.after(manha)) {
							word.set("#"+tokensAux[i]);
							one.set("Manha");
							context.write(word, one);

						} else if (dataFormatada.before(noite) && dataFormatada.after(tarde)) {
							word.set("#"+tokensAux[i]);
							one.set("Tarde");
							context.write(word, one);

						}


					}


				}


			}				
		}
	}

}