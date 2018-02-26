#include <GateListener.h>
#include <arduino.h>
#include <ESP8266WiFi.h>
#include <WiFi.h>
// WiFiUDP Udp;
GateListener::GateListener (uint8_t _portd,uint8_t _port, char _serialMsg, uint16_t _treshold) {

  isBallIn = false;
  port = _port;
  portd=_portd;
  serialMsg = _serialMsg;
  treshold = _treshold;
  count = 0;
 // Udp.begin(localUdpPort);
}

void GateListener::check(  WiFiClient *client) {

    digitalWrite(portd, 1);
  uint16_t current_value = analogRead(port);


   //Serial.print(client.status());
   if (current_value < (treshold)) {
     isBallIn = true;
     timer_ms = millis() + 300;
   }
 //Serial.println(isBallIn);
   if ((current_value > treshold) && isBallIn) {
     count++;
     Serial.print(serialMsg);Serial.println(count);
     isBallIn = false;
     // Serial.println(String(millis()) + " connect " + client.connect("10.0.1.216", 4444));
     // Serial.println(String(millis()) + " send " + client.print(serialMsg));
     //  delay(1000);
     // Serial.println(String(millis()) + " stop ");
     //
     // client.stop();
     Serial.println();

     Serial.print(client->println(serialMsg));
    delay(3000);
     //client.connect("10.0.1.216", 4444);
   }
//  Serial.print(serialMsg);
//Serial.println(current_value);

   if (timer_ms < millis() && isBallIn) {
     isBallIn = false;
   }
     digitalWrite(portd,0);
}
