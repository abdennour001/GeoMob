package com.example.geomob

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geomob.models.Country
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var db: CountryDB? = null
    private var dao: CountryDAO? = null

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var adapter: RecyclerAdapter
    private lateinit var countriesListRecycler: RecyclerView

    companion object {
        internal var countriesList: List<Country>? = listOf(
        Country(1, "Algeria", "Algeria is a North African country with a Mediterranean coastline and a Saharan desert interior. Many empires have left legacies here, such as the ancient Roman ruins in seaside Tipaza. In the capital, Algiers, Ottoman landmarks like circa-1612 Ketchaoua Mosque line the hillside Casbah quarter, with its narrow alleys and stairways. The city’s Neo-Byzantine basilica Notre Dame d’Afrique dates to French colonial rule.", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Flag_of_Algeria.svg/1280px-Flag_of_Algeria.svg.png", 2.5, 4.3, listOf("Gaz", "Petrol"), listOf("Algerian"), listOf("https://live.staticflickr.com/4423/36604682811_1a768774f6_b.jpg", "https://www.apc-algercentre.dz/images/Alger%20centre%20vue%20de%20haut.jpg", "https://i.ytimg.com/vi/G1ruqm3iVEU/maxresdefault.jpg", "https://transformers-magazine.com/files/bad5624cc86e63e5d60645e4f808fc7a.jpg"), "https://upload.wikimedia.org/wikipedia/commons/7/79/National_anthem_of_Algeria%2C_by_the_U.S._Navy_Band.oga"),
        Country(2, "Spain", "Spain, a country on Europe’s Iberian Peninsula, includes 17 autonomous regions with diverse geography and cultures. Capital city Madrid is home to the Royal Palace and Prado museum, housing works by European masters. Segovia has a medieval castle (the Alcázar) and an intact Roman aqueduct. Catalonia’s capital, Barcelona, is defined by Antoni Gaudí’s whimsical modernist landmarks like the Sagrada Família church.", "https://external-preview.redd.it/sgupg2QyvwFm7eLaH0isYTSx1IAYT2cnG9EG2qaK7dc.png?auto=webp&s=c2fe73665a3b109d9a040fb4f70fcba4e2875149", 1.5, 2.5, listOf("Brain"), listOf("Spanish"), listOf("https://lp-cms-production.imgix.net/2019-06/73acceaafa620817a58081310e91e479-spain.jpeg?fit=crop&q=40&sharp=10&vib=20&auto=format&ixlib=react-8.6.4", "https://www.astons.com/assets/residences/spain/hero-8f0133ab87e65c31088b905f4e76f93ef899472e5c78df5ea29552313a9042a4.jpg", "https://www.bollore-logistics.com/PublishingImages/FOCUS/Market%20Focus%20SPAIN%20Cover.jpg", "https://www.somtoseeks.com/wp-content/uploads/2018/07/The-Ultimate-Spain-Travel-Guide-Skyline-of-Madrid-1.jpg"), "https://upload.wikimedia.org/wikipedia/commons/c/c8/Marcha_Real-Royal_March_by_US_Navy_Band.ogg"),
        Country(3, "Morocco", "Morocco, a North African country bordering the Atlantic Ocean and Mediterranean Sea, is distinguished by its Berber, Arabian and European cultural influences. Marrakesh’s medina, a mazelike medieval quarter, offers entertainment in its Djemaa el-Fna square and souks (marketplaces) selling ceramics, jewelry and metal lanterns. The capital Rabat’s Kasbah of the Udayas is a 12th-century royal fort overlooking the water.", "https://i.pinimg.com/originals/fa/3d/a4/fa3da46b641353a80f571ca936163a5e.jpg", 2.6, 5.5, listOf("Gaz", "Petrol"), listOf("Moroco"), listOf("https://res.cloudinary.com/stay-list/image/upload/b_rgb:000000,co_rgb:ffffff,o_90,w_2800,f_auto/v1549609635/area/morocco-l", "https://heremag-prod-app-deps-s3heremagassets-bfie27mzpk03.s3.amazonaws.com/wp-content/uploads/2020/01/15175132/chefchaouen-morocco-heidi-kaden-NncAbldgViA-unsplash-1200x800.jpg", "https://cdn.kimkim.com/files/a/content_articles/featured_photos/7f216ea0df57884af8e5eb4dd89b28acef94d503/big-c163aec2c4f3ae1e466f32daf73b3eeb.jpg"), "https://upload.wikimedia.org/wikipedia/commons/3/3f/National_Anthem_of_Morocco.ogg"),
        Country(4, "Russia", "Russia, or the Russian Federation, is a transcontinental country located in Eastern Europe and Northern Asia.", "https://i1.wp.com/www.youngpioneertours.com/wp-content/uploads/2020/03/russian-flag-russian-flag-russia-flag-of-russia.jpg?fit=1332%2C850&ssl=1", 17.1, 144.5, listOf("Gaz", "Petrol"), listOf("Moroco"), listOf("https://media.pri.org/s3fs-public/images/2019/06/2019-06-04-russia02-01-4-lede.jpg", "https://ei.marketwatch.com/Multimedia/2019/03/25/Photos/ZQ/MW-HG277_kremli_20190325082720_ZQ.jpg?uuid=55e0d890-4ef9-11e9-a982-9c8e992d421e", "https://isthatplacesafe.com/wp-content/uploads/2018/12/Russia.jpg", "https://static.themoscowtimes.com/image/1360/9c/3c5ec63cab8e423ca20b10cf5db65156.jpg"), "https://upload.wikimedia.org/wikipedia/commons/4/41/National_Anthem_of_Russia_%282000%29%2C_instrumental%2C_one_verse.ogg"),
        Country(5, "Turkey", "Turkey, officially the Republic of Turkey, is a transcontinental country located mainly on the Anatolian peninsula in Western Asia, with a smaller portion on the Balkan peninsula in Southeastern Europe.", "https://cdn.webshopapp.com/shops/94414/files/52434394/flag-of-turkey.jpg", 0.78, 82.0, listOf("Gaz", "Petrol"), listOf("Moroco"), listOf("https://i2.wp.com/www.middleeastmonitor.com/wp-content/uploads/2017/02/Kocatepe_Camii_Ankara-e1486371334776.jpg?resize=1200%2C766&quality=85&strip=all&ssl=1", "https://previews.123rf.com/images/seqoya/seqoya1507/seqoya150700126/42099055-istanbul-the-capital-of-turkey-eastern-tourist-city-.jpg"), "https://upload.wikimedia.org/wikipedia/commons/5/50/IstiklalMarsi-2013.ogg")
        )
    }

    override fun onStart() {
        super.onStart()
        getCountries()
    }

    private fun getCountries() {
        initDB()
    }

    private fun initDB() {
        var act = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                act.db = CountryDB.getInstance(act)
                act.dao = db?.countryDAO()
                //countriesList = act.dao?.getCountries() as List<Country>
                return null
            }

            override fun onPostExecute(result: Void?) {
                countriesListRecycler = country_list
                linearLayoutManager = LinearLayoutManager(act)
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                countriesListRecycler.layoutManager = linearLayoutManager
                Log.d("##########", countriesList.toString())
                adapter = RecyclerAdapter(countriesList)
                countriesListRecycler.adapter = adapter
                adapter.notifyItemRangeInserted(countriesListRecycler.size-1, countriesListRecycler.size)

            }
        }.execute()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
