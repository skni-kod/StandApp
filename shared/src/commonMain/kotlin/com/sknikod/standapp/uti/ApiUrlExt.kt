package com.sknikod.standapp.uti

fun Const.ApiUrl.footerLinks() = this.url + "footer_links/"
fun Const.ApiUrl.sponsors() = this.url + "sponsors/"
fun Const.ApiUrl.profiles() = this.url + "profiles/"
fun Const.ApiUrl.projects() = this.url + "projects/"
fun Const.ApiUrl.section() = this.url + "section/"
fun Const.ApiUrl.hardware() = this.url + "hardwares/"
fun Const.ApiUrl.hardwareRentals() = this.url + "hardware_rentals/"
fun Const.ApiUrl.gallery() = this.url + "gallery/"
fun Const.ApiUrl.files() = this.url + "files/"
fun Const.ApiUrl.tags() = this.url + "tags/"
fun Const.ApiUrl.comments() = this.url + "comments/"
fun Const.ApiUrl.articles() = this.url + "articles/"
fun Const.ApiUrl.groups() = this.url + "groups/"
fun Const.ApiUrl.users() = this.url + "users/"
fun Const.ApiUrl.image(path: String) = this.url + path
fun Url.specified(id: Int) = this + "$id/"
