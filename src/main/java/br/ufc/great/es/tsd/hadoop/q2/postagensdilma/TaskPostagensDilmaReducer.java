package br.ufc.great.es.tsd.hadoop.q2.postagensdilma;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TaskPostagensDilmaReducer extends Reducer<Text,Text,Text,Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		for (Text val : values) {
			context.write(key, val);
		}
		
	}
}