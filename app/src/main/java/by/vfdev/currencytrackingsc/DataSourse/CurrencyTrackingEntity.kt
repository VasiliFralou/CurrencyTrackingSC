package by.vfdev.currencytrackingsc.DataSourse


data class CurrencyTrackingEntity(
    val base: String,
    val date: String,
    val rates: List<Rates>,
    val timestamp: Int)