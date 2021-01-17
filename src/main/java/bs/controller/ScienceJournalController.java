package bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import bs.entity.ScienceJournal;
import bs.service.ScienceJournalService;

@RestController
@RequestMapping("/science")

public class ScienceJournalController {
	@Autowired
	ScienceJournalService scienceJournalService;


	@RequestMapping(method = RequestMethod.POST)
	public String newJournal(@RequestBody ScienceJournal journal) {
		return scienceJournalService.save(journal);
	}

	@RequestMapping(value = "/{barcode}", method = RequestMethod.GET)
	public ScienceJournal journalInfo(@PathVariable String barcode) {

		return scienceJournalService.findByBarcode(barcode);
	}

	@RequestMapping(value = "/{barcode}", method = RequestMethod.PUT)
	public ScienceJournal updateJournal(@RequestBody ScienceJournal journal, @PathVariable String barcode) {
		return scienceJournalService.update(journal, barcode);

	}
	@RequestMapping(method = RequestMethod.GET)
	public List<ScienceJournal> findAll() {
		return scienceJournalService.findAll();
	}

	
}
