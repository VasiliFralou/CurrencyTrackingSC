package by.vfdev.currencytrackingsc.remotemodel.currency


data class CurrencyTrackingEntity(
    val base: String,
    val date: String,
    val rates: List<Rates>,
    val timestamp: Int)