package br.ufc.great.es.tsd.hadoop.q2.postagenshoradiames;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import br.ufc.great.es.tsd.hadoop.q2.util.HoraId;

public class TokenizerPostagensHoraDiaMesMapper extends Mapper<Object, Text, Text, Text>{
	private Text word = new Text();
	private Text one = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String linha="";
		linha = value.toString();

		if (linha != null) {
			// Utiliza a funcao split da versao jdk1.4
			String[] tokens = linha.split("\t");

			// separa por espacos em branco o token 1 - comentario			
			SimpleDateFormat formato2 = new SimpleDateFormat("dd");			
			SimpleDateFormat formato4 = new SimpleDateFormat("HH:mm:ss");

			// separa o token 7 - data
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

			Date dataFormatadaHora = null;
			try {
				dataFormatadaHora = formato4.parse(tokensData[3]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
			
			int hora = dataFormatadaHora.getHours();

			String id = tokens[0];
			HoraId horaId = new HoraId(String.valueOf(hora), id);

			word.set(String.valueOf(dia));
			one.set(horaId.getHora() + "," + horaId.getId());
			context.write(word, one);
		}
	}
}