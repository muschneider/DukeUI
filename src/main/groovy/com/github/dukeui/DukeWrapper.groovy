package com.github.dukeui

import no.priv.garshol.duke.ConfigLoader;
import no.priv.garshol.duke.Configuration;
import no.priv.garshol.duke.DataSource;
import no.priv.garshol.duke.Processor;
import no.priv.garshol.duke.Record;
import no.priv.garshol.duke.RecordIterator;

class DukeWrapper {
	
	def exec(configFile) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<HashMap<String, String>> records = new ArrayList<HashMap<String, String>>();

        try {
            Configuration config = ConfigLoader.load(configFile);

            DataSource ds = config.getDataSources().iterator().next();
            
            def headerData = []
            Record header = ds.getRecords().next();
            for(String fieldName:header.getProperties()) {
                headerData << fieldName
            }
            result.put("header",headerData)
            

            RecordIterator ri = ds.getRecords();
            while(ri.hasNext()) {
                Record record = ri.next();
                
                def dataValue = [:]
                for(String fieldName:record.getProperties()) {
                    dataValue.put(fieldName, record.getValue(fieldName));
                }
                records.add(dataValue);
            }
        }
        catch(Exception e) {
            e.printStackTrace()
        }
		result.put("records", records);
        result
	}
	
}
