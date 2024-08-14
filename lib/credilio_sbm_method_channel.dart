import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'credilio_sbm_platform_interface.dart';

/// An implementation of [CredilioSbmPlatform] that uses method channels.
class MethodChannelCredilioSbm extends CredilioSbmPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('credilio_sbm');

  @override
  Future<void> openLibrary({
    required token,
    required String email,
    required String url,
  }) async {
    try {
      await methodChannel.invokeMethod('openLibrary', {
        'token': token,
        'email': email,
        'url': url,
      });
    } on PlatformException catch (e) {
      debugPrint('Error: ${e.message}');
    }
  }
}
