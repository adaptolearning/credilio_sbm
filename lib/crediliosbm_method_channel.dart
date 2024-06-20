import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'crediliosbm_platform_interface.dart';

/// An implementation of [CrediliosbmPlatform] that uses method channels.
class MethodChannelCrediliosbm extends CrediliosbmPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('crediliosbm');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
