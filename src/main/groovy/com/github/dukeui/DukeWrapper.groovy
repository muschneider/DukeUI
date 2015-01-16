package com.github.dukeui

import no.priv.garshol.duke.ConfigLoader;
import no.priv.garshol.duke.Configuration;
import no.priv.garshol.duke.DataSource;
import no.priv.garshol.duke.Processor;
import no.priv.garshol.duke.Record;
import no.priv.garshol.duke.RecordIterator;

class DukeWrapper {
	
	def execute() {
		"test of DukeWrapper "
	}

	
	def run(configFile) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, String>> records = new ArrayList<HashMap<String, String>>();
		
		Configuration config = ConfigLoader.load(new File(configFile));

		DataSource ds = config.getDataSources().iterator().next();
		
		Record header = ds.getRecords().next();
		for(String fieldName:header.getProperties()) {
			System.out.print( fieldName+", ");
		}
		System.out.println();
		
		RecordIterator ri = ds.getRecords();
		while(ri.hasNext()) {
			Record record = ri.next();
			
			HashMap<String, String> dataValue = new HashMap<String, String>();
			for(String fieldName:record.getProperties()) {
				//System.out.print(record.getValue(fieldName)+", ");
				dataValue.put(fieldName, record.getValue(fieldName));
			}
			records.add(dataValue);
			//System.out.println();
		}
		result.put("records", records);
	}
	
}
