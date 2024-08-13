import 'crediliosbm_platform_interface.dart';

class Crediliosbm {
  Future<void> openLibrary(
      {required token, required String email, required String url}) {
    return CrediliosbmPlatform.instance.openLibrary(
      token: token,
      email: email,
      url: url,
    );
  }
}
