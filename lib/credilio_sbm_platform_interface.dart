import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'credilio_sbm_method_channel.dart';

abstract class CredilioSbmPlatform extends PlatformInterface {
  /// Constructs a CredilioSbmPlatform.
  CredilioSbmPlatform() : super(token: _token);

  static final Object _token = Object();

  static CredilioSbmPlatform _instance = MethodChannelCredilioSbm();

  /// The default instance of [CredilioSbmPlatform] to use.
  ///
  /// Defaults to [MethodChannelCredilioSbm].
  static CredilioSbmPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [CredilioSbmPlatform] when
  /// they register themselves.
  static set instance(CredilioSbmPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<void> openLibrary({
    required String token,
    required String url,
  }) {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
