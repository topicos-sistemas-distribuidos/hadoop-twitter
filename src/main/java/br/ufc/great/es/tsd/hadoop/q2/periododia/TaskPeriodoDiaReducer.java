package br.ufc.great.es.tsd.hadoop.q2.periododia;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TaskPeriodoDiaReducer extends Reducer<Text,Text,Text,Text> {
	private Text result = new Text();

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		String cadeia = "";
		for (Text val : values) {
			cadeia = cadeia + ", " + val;
		}
		result.set(cadeia);
		context.write(key, result);
	}
}