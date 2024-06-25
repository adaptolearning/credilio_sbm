import 'crediliosbm_platform_interface.dart';

class Crediliosbm {
  Future<void> openLibrary({required token, required String email}) {
    return CrediliosbmPlatform.instance.openLibrary(token: token, email: email);
  }
}
