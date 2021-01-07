package com.arkhelais.scottishmunros;

import com.arkhelais.scottishmunros.model.Munro;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScottishmunrosApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ScottishmunrosApplication.class, args);

		String fileName = "munrotab_v6.2.csv";

		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
			List<String[]> r = reader.readAll();
			r.forEach(x -> System.out.println(Arrays.toString(x)));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} catch (CsvException e) {
			System.out.println("CsvException: " + e.getMessage());
		}

		CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // custom separator
		try(CSVReader reader = new CSVReaderBuilder(
				new FileReader(fileName))
				.withCSVParser(csvParser)   // custom CSV parser
				.withSkipLines(1)           // skip the first line, header info
				.build()){
			List<String[]> r = reader.readAll();
			r.forEach(x -> System.out.println(Arrays.toString(x)));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} catch (CsvException e) {
			System.out.println("CsvException: " + e.getMessage());
		}

		List<Munro> beans = new ArrayList<>();
		try {
			CsvToBeanBuilder<Munro> csvToBeanBuilder = new CsvToBeanBuilder<>(new FileReader(fileName));
			beans = csvToBeanBuilder
					.withType(Munro.class)
					.build()
					.parse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//beans.forEach(System.out::println);
		System.out.println("beans.size() = " + beans.size());
		Stream<Munro> catNot = beans.stream().filter(m -> m.getCategory().isEmpty());
		Stream<Munro> catMunro = beans.stream().filter(m -> m.getCategory().equals("MUN"));
		Stream<Munro> catTop = beans.stream().filter(m -> m.getCategory().equals("TOP"));
		Stream<Munro> catMunroTop = beans.stream().filter(m -> m.getCategory().equals("MUN") || m.getCategory().equals("TOP"));
		catNot.sorted(
				Comparator.comparingDouble(Munro::getHeight)
						.reversed()
						.thenComparing(Munro::getName)
						.reversed())
				.limit(5)
				.forEach(System.out::println);
	}


}
