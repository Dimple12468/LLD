import com.Cache;
import com.CacheService;
import com.EvictionPolicy;

public class App {
    public static void main(String[] args) {
        CacheService cacheService= CacheService.getInstance();
        Cache cache = cacheService.init(10,EvictionPolicy.LRU);
        cacheService.put("First","Dimple");
        System.out.println(cacheService.get("First"));

    }
}
