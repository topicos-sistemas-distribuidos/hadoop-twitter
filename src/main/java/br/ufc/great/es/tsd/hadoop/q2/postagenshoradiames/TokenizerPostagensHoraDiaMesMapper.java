package br.ufc.great.es.tsd.hadoop.q2.postagenshoradiames;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerPostagensHoraDiaMesMapper extends Mapper<Object, Text, Text, Text>{
	private Text word = new Text();
	private Text one = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String linha="";
		linha = value.toString();

		if (linha != null) {
		}

	}
}