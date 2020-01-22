package jpa.dsd;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 * Demonstrate use of a DeltaSpike-Data-based List service.
 * Most of this example would not be needed when running
 * in an EE container.
 */
public class DSDataDemo {
    public static void main(String[] args) {

        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();

        // Start the CDI contexts for beans we need
        ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        contextControl.startContext(SessionScoped.class);

        // Now use CDI to locate the Lister object
        ChapterList chapLister = 
        	BeanProvider.getContextualReference(ChapterList.class, false);
        
        // These two statements are all you'd need in an EE application
        List<Chapter> chaps = chapLister.findAll();
        // (though you'd likely generate HTML instead of plain text).l
        chaps.forEach(System.out::println);
        
        // All done, bye-bye
        cdiContainer.shutdown();
    }
}
