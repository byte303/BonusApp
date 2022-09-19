package frog.company.bonusapp.model

import frog.company.bonusapp.R
import java.io.Serializable

data class Card(
    var id : Int = 0,
    var image : Int = 0,
    var color : Int = 0,
    var balance : Double = 0.0,
    var name : String = "",
    var status : Int = 0
) : Serializable{
    fun onLoad(): ArrayList<Card> {

        return arrayListOf(
            Card(
                0,
                R.drawable.metro,
                R.color.color_metro,
                0.0,
                "Метро"
            ),
            Card(
                1,
                R.drawable.fozzy,
                R.color.color_fozzy,
                0.0,
                "Fozzy"
            ),
            Card(
                2,
                R.drawable.ashan,
                R.color.color_ashan,
                0.0,
                "Ашан"
            ),
            Card(
                3,
                R.drawable.white_flower,
                R.color.color_white_flower,
                0.0,
                "Белая ромашка"
            ),
            Card(
                4,
                R.drawable.kari,
                R.color.color_kari,
                0.0,
                "Kari"
            ),
            Card(
                5,
                R.drawable.silpo,
                R.color.color_silpo,
                0.0,
                "Сильпо"
            ),
            Card(
                6,
                R.drawable.fora,
                R.color.color_fora,
                0.0,
                "Фора"
            ),
            Card(
                7,
                R.drawable.ringo,
                R.color.color_ringo,
                0.0,
                "Ringoo"
            ),
            Card(
                8,
                R.drawable.five,
                R.color.color_five,
                0.0,
                "Пятерочка"
            ),
            Card(
                9,
                R.drawable.sport,
                R.color.color_metro,
                0.0,
                "Спортмастер"
            ),
            Card(
                10,
                R.drawable.magnit,
                R.color.color_five,
                0.0,
                "Магнит"
            )
        )
    }
}