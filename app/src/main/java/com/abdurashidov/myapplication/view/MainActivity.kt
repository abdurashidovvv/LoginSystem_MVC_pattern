package com.abdurashidov.myapplication.viewimport android.os.Bundleimport android.widget.Toastimport androidx.appcompat.app.AppCompatActivityimport com.abdurashidov.myapplication.controller.LoginControllerimport com.abdurashidov.myapplication.databinding.ActivityMainBindingimport com.abdurashidov.myapplication.model.ModelModuleimport com.abdurashidov.myapplication.model.observer.AccountObserverclass MainActivity:AppCompatActivity(), LoginView, AccountObserver{    private val model=ModelModule.dataAccessLayer    private val controller= LoginController()    private lateinit var binding: ActivityMainBinding    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        binding= ActivityMainBinding.inflate(layoutInflater)        setContentView(binding.root)        controller.bind(this)        binding.loginBtn.setOnClickListener {            controller.onLoginButtonClicked()        }    }    override fun onStart() {        super.onStart()        model.register(this)    }    override fun onStop() {        super.onStop()        model.unregister(this)    }    override fun getUserName(): String = binding.username.text.toString()    override fun getPassword(): String = binding.password.text.toString()    override fun accountLoggedIn() {        val account = queryAccount()        showMessage("${account.firstName} ${account.lastName}")    }    override fun accountUnknown() {        showMessage("Username or password incorrect. Please, try again.!")    }    private fun showMessage(message: String) {        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()    }    private fun queryAccount()=model.getCurrentAccount()}