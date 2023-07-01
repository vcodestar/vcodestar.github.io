#!/usr/bin/env python3

import rclpy
from geometry_msgs.msg import Twist
import time

def main():
    rclpy.init()
    node = rclpy.create_node("rotate_node")
    publisher = node.create_publisher(Twist, "cmd_vel", 10)

    
    twist = Twist() # init twist
    twist.angular.z = 0.0 # set rotation speed to 0

    start_time = time.time() # start timer

    # Part A
    # 0-4sec accelarate 
    while time.time() - start_time < 4:
        twist.angular.z += 0.0084375 
        publisher.publish(twist)
        time.sleep(1)
    print("angular z =", twist.angular.z)
    print("Stopped Accelarating")

    # 4-16sec keep same speed 
    while time.time() - start_time < 16:
        publisher.publish(twist)
        time.sleep(1)

    # 16-20sec decelarate
    print("Starting to decelarate")
    while time.time() - start_time < 20:
        twist.angular.z -= 0.0084375 
        publisher.publish(twist)
        time.sleep(1)
    print("angular z =", twist.angular.z)

    twist.linear.x = 0.0
    start_time = time.time() # reset timer for Part B

    # 0-12sec accelarate
    while time.time() - start_time < 12:
        twist.linear.x += 0.0101231804 
        publisher.publish(twist)
        time.sleep(1)
    print("linear x =", twist.linear.x)
    print("Stopped Accelarating")

    # 12-48sec keep same speed
    while time.time() - start_time < 48:
        publisher.publish(twist)
        time.sleep(1)
    
    # 48-60sec decelarate
    print("Starting to decelarate")
    while time.time() - start_time < 60:
        twist.linear.x -= 0.0101231804 
        publisher.publish(twist)
        time.sleep(1)
    print("linear x =", twist.linear.x)

    start_time = time.time() # reset timer for Part C

    # 0-4sec accelarate 
    while time.time() - start_time < 4:
        twist.angular.z += 0.01825 
        publisher.publish(twist)
        time.sleep(1)
    print("angular z =", twist.angular.z)
    print("Stopped Accelarating")

    # 4-16sec keep same speed 
    while time.time() - start_time < 16:
        publisher.publish(twist)
        time.sleep(1)

    # 16-20sec decelarate
    print("Starting to decelarate")
    while time.time() - start_time < 20:
        twist.angular.z -= 0.01825 
        publisher.publish(twist)
        time.sleep(1)
    print("angular z =", twist.angular.z)

    node.destroy_node()
    rclpy.shutdown()
#### end of main ###############################

if __name__ == "__main__":
    main()

