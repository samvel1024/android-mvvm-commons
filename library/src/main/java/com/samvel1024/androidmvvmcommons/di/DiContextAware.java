package com.samvel1024.androidmvvmcommons.di;

/**
 * The application which is going to apply
 * DiContainer pattern has to implement this interface.
 * DiContext is a class which includes all the services
 * required by the application mainly for data retrieval.
 * Like API access, local database or preference storage.
 *
 * This is done mainly for testability purposes, so
 * a subclass of the DiContainer can be passed to the
 * whole application which will simulate mock data
 * retrieval and persistence.
 *
 * See DiUtil methods.
 *
 * example.
 *
 * class DiContainer{
 *
 *      private UserService userService;
 *
 *      public DiContainer(){
 *          this.userService = new RestUserService();
 *      }
 *
 *      public UserService getUserService(){
 *          return userService;
 *      }
 *
 * }
 *
 *
 * public class MyApplication extends Application implements DiContextAware<DiContainer> {
 *
 *     DiContainer container;
 *
 *     public void onCreate(){
 *         container = new DiContainer();
 *     }
 *
 *     public DiContainer getDiContext(){
 *         return container;
 *     }
 *
 * }
 *
 *
 * public class MyActivity extends Activity {
 *
 *     protected void onCreate(SavedInstanceState state){
 *         super.onCreate();
 *
 *         DiContainer container = DiUtil.from(this);
 *         User user = container.getUserService().getUser();
 *
 *     }
 *
 * }
 *
 *
 * @author Samvel Abrahamyan
 */

public interface DiContextAware <T> {

    T getDiContext();

}
