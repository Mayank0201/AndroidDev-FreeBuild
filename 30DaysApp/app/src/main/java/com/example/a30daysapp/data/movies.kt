package com.example.a30daysapp.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a30daysapp.R

data class Movie(
    @StringRes val day: Int,
    @StringRes val movieName: Int,
    @StringRes val theme: Int,
    @DrawableRes val imgId: Int,
    @StringRes val about: Int
)

val Movies = listOf(
    Movie(R.string.day1, R.string.movie_day1, R.string.theme_sunday, R.drawable.movie1, R.string.day1_desc),
    Movie(R.string.day2, R.string.movie_day2, R.string.theme_monday, R.drawable.movie2, R.string.day2_desc),
    Movie(R.string.day3, R.string.movie_day3, R.string.theme_tuesday, R.drawable.movie3, R.string.day3_desc),
    Movie(R.string.day4, R.string.movie_day4, R.string.theme_wednesday, R.drawable.movie4, R.string.day4_desc),
    Movie(R.string.day5, R.string.movie_day5, R.string.theme_thursday, R.drawable.movie5, R.string.day5_desc),
    Movie(R.string.day6, R.string.movie_day6, R.string.theme_friday, R.drawable.movie6, R.string.day6_desc),
    Movie(R.string.day7, R.string.movie_day7, R.string.theme_saturday, R.drawable.movie7, R.string.day7_desc),
    Movie(R.string.day8, R.string.movie_day8, R.string.theme_sunday, R.drawable.movie8, R.string.day8_desc),
    Movie(R.string.day9, R.string.movie_day9, R.string.theme_monday, R.drawable.movie9, R.string.day9_desc),
    Movie(R.string.day10, R.string.movie_day10, R.string.theme_tuesday, R.drawable.movie10, R.string.day10_desc),
    Movie(R.string.day11, R.string.movie_day11, R.string.theme_wednesday, R.drawable.movie11, R.string.day11_desc),
    Movie(R.string.day12, R.string.movie_day12, R.string.theme_thursday, R.drawable.movie12, R.string.day12_desc),
    Movie(R.string.day13, R.string.movie_day13, R.string.theme_friday, R.drawable.movie13, R.string.day13_desc),
    Movie(R.string.day14, R.string.movie_day14, R.string.theme_saturday, R.drawable.movie14, R.string.day14_desc),
    Movie(R.string.day15, R.string.movie_day15, R.string.theme_sunday, R.drawable.movie15, R.string.day15_desc),
    Movie(R.string.day16, R.string.movie_day16, R.string.theme_monday, R.drawable.movie16, R.string.day16_desc),
    Movie(R.string.day17, R.string.movie_day17, R.string.theme_tuesday, R.drawable.movie17, R.string.day17_desc),
    Movie(R.string.day18, R.string.movie_day18, R.string.theme_wednesday, R.drawable.movie18, R.string.day18_desc),
    Movie(R.string.day19, R.string.movie_day19, R.string.theme_thursday, R.drawable.movie19, R.string.day19_desc),
    Movie(R.string.day20, R.string.movie_day20, R.string.theme_friday, R.drawable.movie20, R.string.day20_desc),
    Movie(R.string.day21, R.string.movie_day21, R.string.theme_saturday, R.drawable.movie21, R.string.day21_desc),
    Movie(R.string.day22, R.string.movie_day22, R.string.theme_sunday, R.drawable.movie22, R.string.day22_desc),
    Movie(R.string.day23, R.string.movie_day23, R.string.theme_monday, R.drawable.movie23, R.string.day23_desc),
    Movie(R.string.day24, R.string.movie_day24, R.string.theme_tuesday, R.drawable.movie24, R.string.day24_desc),
    Movie(R.string.day25, R.string.movie_day25, R.string.theme_wednesday, R.drawable.movie25, R.string.day25_desc),
    Movie(R.string.day26, R.string.movie_day26, R.string.theme_thursday, R.drawable.movie26, R.string.day26_desc),
    Movie(R.string.day27, R.string.movie_day27, R.string.theme_friday, R.drawable.movie27, R.string.day27_desc),
    Movie(R.string.day28, R.string.movie_day28, R.string.theme_saturday, R.drawable.movie28, R.string.day28_desc),
    Movie(R.string.day29, R.string.movie_day29, R.string.theme_sunday, R.drawable.movie29, R.string.day29_desc),
    Movie(R.string.day30, R.string.movie_day30, R.string.theme_monday, R.drawable.movie30, R.string.day30_desc),
    Movie(R.string.day31, R.string.movie_day31, R.string.theme_tuesday, R.drawable.movie31, R.string.day31_desc)
)