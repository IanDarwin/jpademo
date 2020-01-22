package jpa.dsd;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@ApplicationScoped @Dependent @Repository @Named("chapterList")
public interface ChapterList extends EntityRepository<Chapter, Long> {

}
