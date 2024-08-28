import 'dart:convert';

import 'package:credilio_sbm/credilio_sbm.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final _credilioSbmPlugin = CredilioSbm();
  final credilioToken =
      "NzQ3.TxqnQ6gqBWBzG9BJlgW3yKDh5dI_8TVvPd1MjbT4Yt1mClxXzczLYJn9t1EZ";
  final generateTokenUrl =
      'https://d14-api.novio.co.in/customer/v1/spense/generate-token';

  var params = {
    'module_type': 'LANDING',
    'terms_and_condition': true
  };

  final Dio _dio = Dio();

  @override
  void initState() {
    super.initState();
  }

  Future<void> _getToken() async {
    try {
      // Make the POST request with token in headers
      Response response = await _dio.post(
        generateTokenUrl,
        data: jsonEncode(params),
        options: Options(
          headers: {
            'Content-Type': 'application/json',
            'Authorization':
                'Bearer $credilioToken', // Replace YourTokenHere with actual token
          },
        ),
      );

      if (response.statusCode == 200) {
        // Handle successful response here
        final data = response.data;

        debugPrint('Response Data: $data');

        // Extract the token from the response data
        final token = data['data']['token']; // Correctly access nested 'token'
        final url = data['data']['url']; // Correctly access dynamic 'url'
        debugPrint('Token: $token');
        debugPrint('Url: $url');

        // Now you can use the token with _credilioSbmPlugin.openLibrary()
        await _credilioSbmPlugin.openLibrary(
          token: token,
          url: url,
        );
      } else {
        throw Exception('Failed to get token or url');
      }
    } catch (e) {
      debugPrint('Error: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
      ),
      body: GestureDetector(
        onTap: _getToken,
        child: const Center(
          child: Text('Click Here'),
        ),
      ),
    );
  }
}
