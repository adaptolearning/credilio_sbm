import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'crediliosbm_method_channel.dart';

abstract class CrediliosbmPlatform extends PlatformInterface {
  /// Constructs a CrediliosbmPlatform.
  CrediliosbmPlatform() : super(token: _token);

  static final Object _token = Object();

  static CrediliosbmPlatform _instance = MethodChannelCrediliosbm();

  /// The default instance of [CrediliosbmPlatform] to use.
  ///
  /// Defaults to [MethodChannelCrediliosbm].
  static CrediliosbmPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [CrediliosbmPlatform] when
  /// they register themselves.
  static set instance(CrediliosbmPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<void> openLibrary(String token, String email) {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
