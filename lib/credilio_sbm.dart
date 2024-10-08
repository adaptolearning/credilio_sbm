import 'credilio_sbm_platform_interface.dart';

class CredilioSbm {
  Future<String> openLibrary(
      {required token, required String url}) {
    return CredilioSbmPlatform.instance.openLibrary(
      token: token,
      url: url,
    );
  }
}
