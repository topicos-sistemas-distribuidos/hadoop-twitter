package br.ufc.great.es.tsd.hadoop.q2.postagensdilma;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerPostagensDilmaMapper extends Mapper<Object, Text, Text, Text>{
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

			while (itr.hasMoreTokens()) {
				String myKey = itr.nextToken();

				// verifica se o token contem hashtag
				if (myKey.contains("Dilma") || myKey.contains("dilma") || myKey.contains("DILMA")) {
					word.set("Dilma");
					one.set(tokens[1]);
					context.write(word, one);
				}

			}
		}
	}
}