package start;

import concurs.model.Proba;
import concurs.model.TipProba;
import concurs.service.rest.ServiceException;
import org.springframework.web.client.RestTemplate;
import rest.client.ProbeClient;

public class StartRestClient {
    private final static ProbeClient probeClient=new ProbeClient();
    public static void main(String[] args) {
        Proba proba1 = new Proba(16, TipProba.valueOf("desen"), 10, 13);
        RestTemplate restTemplate=new RestTemplate();
//        show(()->{
//           probeClient.create(proba1) ;
//        });

        show(()->probeClient.getById(100));

        show(()->probeClient.update(proba1));

        show(()->{
            for (Proba proba : probeClient.getAll()) {
                System.out.println(proba);
            }
        });
        show(()->{
            probeClient.delete(100);
        });

    }
    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            //  LOG.error("Service exception", e);
            System.out.println("Service exception"+ e);
        }
    }
}
