package come.geekbrains.vitekm.mvpkotlinproject.user.userinterface

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView : ProgressView, ErrorView, MvpView {

    fun init()

    // Выводит исходное изображение
    fun showOriginImage(uri: Uri)

    // Выводит конвертированное изображение
    fun showConvertedImage(uri: Uri)

    // Кнопку старта  конвертации активная
    fun btnStartConvertEnable()

    // Деактивирует кнопку старта конвертации
    fun btnStartConvertDisabled()

    // Кнопка отмены конвертации активная
    fun btnAbortConvertEnabled()

    // Деактивирует кнопку отменты конвертации
    fun btnAbortConvertDisabled()

    // Показывает заглушку - флажок сигнализирующий о прерывании конвертации
    fun signAbortConvertShow()

    // Скрывает заглушку - флажок сигнализирующий о прерывании конвертации
    fun signAbortConvertHide()

    // Показывает заглушку - флажок призывающий начать чтото делать уже
    fun signGetStartedShow()

    // Скрывает заглушку - флажок призывающий начать чтото делать...
    fun signGetStartedHide()

    // Показывает заглушку - флажок ожидания действия...
    fun signWaitingShow()

    // Скрывает заглушку - флажок ожидания действия...
    fun signWaitingHide()
}