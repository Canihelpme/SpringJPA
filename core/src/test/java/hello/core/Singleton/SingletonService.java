package hello.core.Singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    //getInstance로만 instance호출 가능.
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }
}
