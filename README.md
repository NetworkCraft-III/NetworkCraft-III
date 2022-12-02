# NetworkCraft III
#### CS 481 Capstone Design by Heath Thompson, Maxwell Icardo, Matt Morrison

## Mission: 
#### To create a Minecraft Mod that allows users to build computer networks using blocks.

## Purpose: 
#### To give users foundational knowledge of network concepts.

## Current Progress:
#### - Created blocks/textures for network items such as Laptops, Modems, Routers, and Cables.
#### - Created backend logic to support network-like functionality between blocks.
 - On/Off switches of one block may directly effect other blocks' states within the network. 
 - Blocks are given unique MAC addresses when placed into the world.
 - When connected to a network, a Laptop Block will be assigned an IP address associated with the Router Block it's connected to.
 - Laptop Blocks can only connect to a Router Block if it's within a specified range(10 blocks) of that Router Block.
 - Connections are refused if that connection's associated devices are not properly configured.
#### - Created GUIs for users to interact with devices (blocks).
 - Users can turn devices (blocks) off and on and connect to/disconnect from other devices.
 - Within a Router Block's GUI, a user can disconnect all devices connected to it.
 - Within a Laptop Block's GUI, a user can input IP addresses they'd like the Laptop Block to connect to.
#### - Created user HUD to inform user how to interact with networks properly.
 - World variables track number of Router Blocks and Laptops Blocks placed in the world and are displayed in the upper right corner.
 - Messages update user when a device's state changes within the world and are displayed in the bottom left corner:
      - Successful and unsuccessful connections.
      - Successful and unsuccessful on/off switches.
      - Reasoning why a specific connection was refused.

## Upcoming Features:
#### - Modem/Cable Connectivity
#### - Send messages across networks
