package br.ufc.great.es.tsd.hadoop.q2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class PostagemDia {

	public static void main(String[] args) throws IOException, ParseException {

		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		FileInputStream stream = new FileInputStream("dataset/fragmento.txt");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = "";
		linha = br.readLine();
		while (linha != null) {

			// Utiliza a funcao split da versao jdk1.4
			String[] tokens = linha.split("\"");

			// separa por espacos em branco o token 1 - comentario
			StringTokenizer itr = new StringTokenizer(tokens[1], " ");
			SimpleDateFormat formato2 = new SimpleDateFormat("dd");

			while (itr.hasMoreTokens()) {
				String myKey = itr.nextToken();

				// verifica se o token contem hashtag
				if (myKey.contains("#")) {
					// separa o token 5 - data
					String[] tokensData = tokens[5].split(" ");
					// pega apenas o indice 2 - dia - para adicionar ao hashmap
					Date dataFormatada = formato2.parse(tokensData[2]);
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTime(dataFormatada);
					int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
					hmap.put(myKey, dia);
				}
			}

			// imprime a linha e passa para a proxima
			//System.out.println(linha);
			linha = br.readLine();

		}

		for (String key : hmap.keySet()) {
			// Capturamos o valor a partir da chave
			Integer value = hmap.get(key);
			System.out.println(key + " = " + value);
		}

	}

}