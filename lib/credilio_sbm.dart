import 'credilio_sbm_platform_interface.dart';

class CredilioSbm {
  Future<void> openLibrary(
      {required token, required String email, required String url}) {
    return CredilioSbmPlatform.instance.openLibrary(
      token: token,
      email: email,
      url: url,
    );
  }
}
