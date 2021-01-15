package bs.repository;


import org.springframework.transaction.annotation.Transactional;
import bs.entity.ScienceJournal;


@Transactional
public interface ScienceJournalRepository  extends BookBaseRepository<ScienceJournal> {
}
