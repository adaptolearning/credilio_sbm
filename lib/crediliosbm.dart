
import 'crediliosbm_platform_interface.dart';

class Crediliosbm {
  Future<String?> getPlatformVersion() {
    return CrediliosbmPlatform.instance.getPlatformVersion();
  }
}
