package br.ufc.great.es.tsd.hadoop.q2.postagensdia;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerPostagensDiaMapper extends Mapper<Object, Text, Text, Text>{
	private Text word = new Text();
	private Text one = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String linha="";
		linha = value.toString();

		if (linha != null) {
			
		}
	}

}
