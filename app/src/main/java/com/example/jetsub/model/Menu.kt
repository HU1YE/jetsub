package com.example.jetsub.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.jetsub.R


data class Menu(
    @DrawableRes val image: Int,
    val title: String,
    @StringRes val category: Int,
    val national: String,
    val desc: String,
    val onCart: Int = 0
)

val dummyMenu = listOf(
    Menu(R.drawable.max, "Max Verstappen", R.string.category_redbull, "Belanda", "Max Emilian Verstappen OON adalah seorang pembalap mobil profesional Belgia-Belanda, yang pada saat ini bersaing di bawah bendera Belanda di dalam ajang balap Formula Satu bersama dengan tim Red Bull Racing. Ia adalah putra dari mantan pembalap F1, yaitu Jos Verstappen."),
    Menu(R.drawable.sergio, "Sergio Peres", R.string.category_redbull, "Meksiko", "Sergio Michel Pérez Mendoza, dijuluki \"Checo\", merupakan seorang pembalap mobil professional asal Meksiko. Saat ini, Pérez membalap di dalam ajang Formula Satu untuk tim Red Bull Racing, mendampingi Max Verstappen. Checo pernah membalap bersama dengan tim Sauber, McLaren, dan Force India."),
    Menu(R.drawable.vettel, "Sebastian vettel", R.string.category_redbull, "German", "Sebastian Vettel adalah seorang pembalap Formula Satu asal Jerman. Ia pertama kali turun dalam ajang balapan resmi F1 pada Grand Prix Turki 2006 ketika ia menjadi pembalap tes hari Jumat untuk tim BMW-Sauber pada usia 19 tahun dan 53 hari."),
    Menu(R.drawable.albon, "Alex Albon", R.string.category_redbull, "Thailand", "Alexander Albon Ansusinha merupakan seorang pembalap mobil profesional kelahiran Inggris yang dalam karier profesionalnya memakai paspor Thailand. Ia sekarang berkompetisi di dalam ajang Formula Satu bersama dengan tim Williams. "),
    Menu(R.drawable.fernando, "Fernando Alonso", R.string.category_astonmartin, "Spanyol", "Fernando Alonso Díaz adalah seorang pembalap mobil profesional dari Spanyol. Ia sempat turun di dalam ajang Formula Satu pada rentang waktu musim 2001, 2003 sampai dengan 2018, dan 2021 sampai dengan saat ini."),
    Menu(R.drawable.stroll, "Lance Stroll", R.string.category_astonmartin, "Canada", "Lance Strulovich, lebih dikenal dengan nama Lance Stroll, adalah seorang pembalap mobil profesional Belgia-Kanada, yang bersaing dengan bendera Kanada di dalam ajang Formula Satu. Ia sekarang membalap bersama dengan tim Aston Martin setelah sebelumnya membalap bersama dengan tim Williams dan Racing Point."),
    Menu(R.drawable.oscar, "Oscar Piastri", R.string.category_mclaren, "Australia", "Oscar Piastri adalah seorang pembalap asal Australia yang berkompetisi di Formula Satu pada musim 2023 bersama dengan McLaren, setelah sebelumnya menjadi pembalap cadangan untuk Alpine F1 Team dan menjadi anggota akademi pembalap tim tersebut. "),
    Menu(R.drawable.ricciardo, "Ricciardo Daniel", R.string.category_mclaren, "Australia", "Daniel Joseph Ricciardo AM merupakan seorang pembalap mobil profesional asal Australia. Ia terakhir berkompetisi bersama dengan tim McLaren di Kejuaraan Dunia Formula Satu pada musim 2022. Ricciardo merupakan juara umum ajang Formula 3 Inggris pada tahun 2009. "),
    Menu(R.drawable.lando, "Lando Norris", R.string.category_mclaren, "Inggris", "Lando Norris merupakan seorang pembalap mobil profesional berkewarganegaraan Inggris-Belgia. Ia sekarang berkompetisi di Formula Satu bersama tim McLaren."),
    Menu(R.drawable.russel, "George russel", R.string.category_mercedes, "Inggris", "George William Russell adalah seorang pembalap mobil profesional Inggris yang saat ini membalap di ajang Formula Satu dengan Mercedes. Ia adalah Juara Formula 2 musim 2018 dan juara Seri GP3 musim 2017. "),
    Menu(R.drawable.lewis, "Lewis Hamilton", R.string.category_mercedes, "Inggris", "Sir Lewis Carl Davidson Hamilton MBE adalah seorang pembalap mobil profesional dari Britania Raya, yang pada saat ini membalap untuk tim Mercedes di dalam ajang Formula Satu. Sekarang, ia memiliki gelar dunia terbanyak, yakni 7, dan juga kemenangan, posisi pole, dan finis podium terbanyak."),
    Menu(R.drawable.ocon, "Esteban ocon", R.string.category_alpine, "France", "Esteban José Jean-Pierre Ocon-Khelfane adalah seorang pembalap profesional yang sekarang berkompetisi dalam Formula Satu dengan tim Alpine. Ia pertama kali membalap dalam Formula Satu dengan Manor Motorsport dalam Grand Prix F1 Belgia 2016, menggantikan Rio Haryanto."),
    Menu(R.drawable.gasly, "Pierre Gasly", R.string.category_alpine, "France", "Pierre Gasly merupakan seorang pembalap mobil profesional asal Prancis yang saat ini membalap di ajang Formula Satu bersama tim Alpine. Ia adalah juara Seri GP2 musim 2016, dan runner up pada Seri Formula Renault 3.5 2014 dan Kejuaraan Super Formula 2017. Ia memulai debut F1-nya di GP Malaysia 2017 dengan Toro Rosso."),
)

val dummyBestSellerMenu = dummyMenu.shuffled()