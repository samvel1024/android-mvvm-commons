# Android MVVM Commons

Helper classes to implement MVVM in Android with less common code


- Error handling

- Progress indication

- View lifecycle handling

- View components

- View layer dependency container



#### Error handling

The view component and the view model have to implement corresponding interfaces in `ErrorContract`. This class is mainly designed for simple error notification like toast message or an error dialog box. The view model can delegate the error event to the view by composing the `ErrorVM` class.

    try {
        //tricky stuff
    }catch (Exception e) {
        new ErrorVM(view).onErorr(view, e, "Error performing the action");
    }



### Progress indication

The view which has to show any dialog or progress indicator which is going to perform solely hide/show actions, should implement `ProgressIndicatorView` interface. An utility implementation which is showing a simple dialog box with a progress circle is `ProgressDialogView`

    class MFragment extends Fragment implements ProgressIndicatorView{
        ProgressDialogView dialogView;
        public void onCreateView(...args..){
            dialogView = new ProgressDialogView(getContext());
        }
        @Override
        public void onShowProgress(){
            dialogView.onShowProgress()
        }
        @Override
        public void onHideProgress(){
            dialogView.onHideProgress();
        }
    }

### View lifecycle handling
If the view model is bound to the lifecycle of the container view component it has to implement `LifecycleVM` interface. To avoid any memory leak caused by backgorund thread and hard reference to the view component the view model needs to unsubscribe from any event emitter like event bus or RxJava observable.

If used with RxJava there is an implementation called `RxLifecycleVM` which wraps the `CompositeDisposable` class.

    MyViewModel implements LifecycleVM {
        RxLifecycleVM lifecycleVM = new RxLifecycleVM();

        @Override public void onStart(){
            lifecycleVM.onStart();
            lifecycleVM.addSubscriber(getUsersListFromApi()
                .subscribeOn(...)
                .observeOn(...)
                .subscribe(...));
        }
        @Override public void onDestroy(){
            lifecycleVM.onDestroy();
        }
    }

### View components
All the fragments which use a single view model variable bound to the xml have to extend `SingleVarBindingFragment`. It takes care of calling the lifecycle methods on the view model if it implements `LifecycleVM` interface, and also is responsible for inflating the view and binding the view model to it.

    public class MyFragment extends SingleVarBindingFragment<MyViewModel, MyFragmentBinding>  {
        @Override
        protected int getBindingVarId() {
            return BR.viewModel;
        }
        @Override
        protected HoursMemberListVM onCreateVM() {
            return new MyViewModel();
        }

        @Override
        protected int getLayoutResource() {
            return R.layout.fragment_hours_tracker;
        }
        @Override
        protected void onInitView() {
            //Write any view initialization code here
        }
    }

When using a fragment which is a standalone piece of UI and needs to be displayed in a separate activity, the class `FragmentHolderActivity` can be used. However if there is needed any customization and interaction between the activity the usage of this class is not encouraged.

            FragmentHolderActivity.launch(getContext(), MyFragment.class, MyFragment.createArgs(consultant));

### View layer dependency container
In order to avoid using complex DI injectors a little POJO can be written to contain all the data layer services to provide a way for creating mock testable app instances. However if the service classes are heavy and long to instantiate this method should be avoided.

In your application class

    public class Application extends android.app.Application implements DiContextAware<DiContext>{
        private DiContext diContext;
        @Override
        public void onCreate() {
            super.onCreate();
            this.diContext = new DiContext();
        }
        @Override
        public DiContext getDiContext() {
            return diContext;
        }
    }

DiContext.java

    public class DiContext {
        private final UserService userService;
        public DiContext() {
            this.userService = new UserService();
        }
        public UserService getUserService() {
            return userService;
        }
    }

UI component (fragment, activity)

        public void onCreate(){
            DiContext diContext = DiUtil.from(this);
            ViewModel vm = new ViewModel(diContext.getUserService())
        }


