#include <Arduino.h>
#include <GateListener.h>
#include <ESP8266WiFi.h>
#include <WiFi.h>
WiFiClient client;
void setup(){
Serial.begin(115200);
WiFi.begin("HANDSOME", "handsomeisawesome");
Serial.print("Connecting");
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  Serial.print("Connected, IP address: ");
  Serial.println(WiFi.localIP());
  //client.connect("1.1.1.1", 80);
  while (!client.connect("10.0.1.216", 4444)) {
    Serial.println("Connection failed.");
    delay(100);
  }
    Serial.println("Connection");
  // if (client.connect("10.0.1.216", 4444)) {
  //
  //   } else {
  //
  //
  //   }6

   pinMode(15, OUTPUT);
 	digitalWrite(15, HIGH);
       pinMode(13, OUTPUT);
          // Serial.println(client.connect("10.0.1.216", 4444));
}
GateListener gateA(13,A0, 'a', 600);
GateListener gateB(2,A0, 'b', 90);
void loop() {
  //  Serial.println("test");
  gateA.check(&client);

  gateB.check(&client);
  while (!client.connected()) {
client.connect("10.0.1.216", 4444);
Serial.println("Connection failed1.");
delay(10);
  }
}
