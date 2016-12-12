package views.account.form

import javax.inject.{Inject, Singleton}

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages

case class PasswordChange(password: String, repeatPassword: String)

@Singleton
class PasswordChangeForm @Inject() (implicit val messages: Messages) {
  val Instance = Form {
    mapping(
      "password" -> nonEmptyText(minLength = 5),
      "repeatPassword" -> nonEmptyText(minLength = 5)
    )(PasswordChange.apply)(PasswordChange.unapply).
      verifying(messages("playauthenticate.change_password.error.passwords_not_same"),
        data => data.password != null && !data.password.isEmpty && data.password.equals(data.repeatPassword))
  }
}