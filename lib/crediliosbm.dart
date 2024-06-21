import 'crediliosbm_platform_interface.dart';

class Crediliosbm {
  Future<void> openLibrary(String token, String email) {
    return CrediliosbmPlatform.instance.openLibrary(token, email);
  }
}
