package br.ufc.great.es.tsd.hadoop.q2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import br.ufc.great.es.tsd.hadoop.q2.postagenshoradiames.TaskPostagensHoraDiaMesReducer;
import br.ufc.great.es.tsd.hadoop.q2.postagenshoradiames.TokenizerPostagensHoraDiaMesMapper;

public class TwitterPostagensHoraDiaMes {
	public static void main(String args[]) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

		if (otherArgs.length < 2) {
			System.err.println("Usage: TwitterPostagensHoraDiaMes <in> [<in>...] <out>");
			System.exit(2);
		}

		Job job = Job.getInstance(conf, "TwitterPostagensHoraDiaMes");
		job.setJarByClass(TwitterPostagensHoraDiaMes.class);
		job.setMapperClass(TokenizerPostagensHoraDiaMesMapper.class);
		job.setCombinerClass(TaskPostagensHoraDiaMesReducer.class);
		job.setReducerClass(TaskPostagensHoraDiaMesReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);	
		job.setNumReduceTasks(1);

		for (int i = 0; i < otherArgs.length - 1; ++i) {
			FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
		}

		FileOutputFormat.setOutputPath(job, new Path(otherArgs[otherArgs.length - 1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
