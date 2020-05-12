package com.kat.daggerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    @Inject
    @field:Named("network")
    lateinit var repositry: Repositry
    @Inject
    @field:Named("database")
    lateinit var repositry1: Repositry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerRepositryComponent.create().injectRepositry(this)

        textval.text = repositry.mytext + "...." + repositry1.mytext
    }
}


class Repositry(val mytext: String)

@Component(modules = [FirstModule::class, SecondModule::class])
interface RepositryComponent {
    fun injectRepositry(app: MainActivity)
}


@Module
class FirstModule {
    @Provides
    @Named("network")
    fun networkCall(): Repositry {
        return Repositry("This is Network Call")
    }


}

@Module
class SecondModule {
    @Provides
    @Named("database")
    fun databaseCall(): Repositry {
        return Repositry("This is Database Call")
    }


}
