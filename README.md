# YBMJ

YBMJ (Ybbb Basic Module Java) is a plugin for Paper servers. It provides foundational utilities for configuration management, logging, and text formatting, designed to simplify plugin development and enhance server management. This plugin serves as a framework that other plugins can extend to leverage its utilities.

## Features

- **Configuration Management**: Easily create, load, and save YAML configuration files using `BasicConfigHandler`.
- **Custom Logging**: Utilize `BasicLoggerHandler` for consistent and formatted logging with info, warning, and severe levels.
- **Text Formatting**: Support for legacy (`&` color codes) and MiniMessage text formatting via `LegacyTextHandler`, with utilities for sending formatted messages, stripping formatting, and formatting locations.
- **Plugin Framework**: Extend `PluginProvider` instead of `JavaPlugin` to access built-in logging and other utilities.

## Usage

### Configuration Management with BasicConfigHandler
The `BasicConfigHandler` class provides methods to create, load, and save YAML configuration files. It can be extended by other classes to manage custom configuration files.

**Example: Extending BasicConfigHandler**
To manage a custom configuration file, create a class that extends `BasicConfigHandler`:

```java
import me.deadybbb.ybmj.BasicConfigHandler;
import me.deadybbb.ybmj.PluginProvider;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfigHandler extends BasicConfigHandler {
    public CustomConfigHandler(PluginProvider plugin) {
        super(plugin, "custom_config.yml");
    }

    public void setDefaultValues() {
        reloadConfig();
        config.set("settings.welcome-message", "Welcome to the server!");
        config.set("settings.max-players", 100);
        saveConfig();
    }

    public String getWelcomeMessage() {
        reloadConfig();
        return config.getString("settings.welcome-message", "Welcome!");
    }
}
```

**Usage**:
```java
CustomConfigHandler configHandler = new CustomConfigHandler(plugin);
configHandler.setDefaultValues(); // Sets default values and saves
String welcomeMessage = configHandler.getWelcomeMessage(); // Retrieves value
plugin.getLogger().info("Welcome message: " + welcomeMessage);
```

This creates a `custom_config.yml` file in the plugin's data folder with the specified settings, which can be reloaded or saved as needed.

### Extending PluginProvider
Instead of extending `JavaPlugin`, plugins should extend `PluginProvider` to inherit built-in utilities like `BasicLoggerHandler`. This ensures consistent logging and simplifies plugin setup.

**Example: Extending PluginProvider**
```java
import me.deadybbb.ybmj.PluginProvider;

public class MyPlugin extends PluginProvider {
    @Override
    public void onEnable() {
       // Plugin startup logic
    }

    @Override
    public void onDisable() {
       // Plugin shutdown logic
    }
}
```
The `PluginProvider` class provides a `logger` instance (`BasicLoggerHandler`) for logging messages with the plugin's name as a prefix.

### Logging with BasicLoggerHandler
The `BasicLoggerHandler` class provides methods for logging messages at different levels (info, warning, severe), automatically including the plugin's name.

**Example**:
```java
public class MyPlugin extends PluginProvider {
    @Override
    public void onEnable() {
        logger.info("Plugin started successfully.");
        try {
            // Some operation
            logger.info("Operation completed.");
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());
        }
    }
}
```

**Output**:
```
[MyPlugin] Plugin started successfully.
[MyPlugin] Operation completed.
[MyPlugin] An error occurred: Something went wrong
```

The `logger` is accessible directly from `PluginProvider` and formats messages with the plugin's name for clarity.

### Text Formatting with LegacyTextHandler
The `LegacyTextHandler` class supports both legacy (`&` color codes) and MiniMessage formats for sending formatted messages to players or the console. It also provides utilities for stripping formatting and formatting locations.

**Example: Sending Formatted Messages**
```java
import me.deadybbb.ybmj.LegacyTextHandler;
import org.bukkit.entity.Player;

public void sendWelcomeMessage(Player player) {
    // Legacy format
    LegacyTextHandler.sendFormattedMessage(player, "&aWelcome to the server, &b" + player.getName() + "&a!");
    // MiniMessage format
    LegacyTextHandler.sendFormattedMessage(player, "<gradient:blue:green>Welcome, " + player.getName() + "!</gradient>");
}
```

**Example: Stripping Formatting**
```java
String formatted = "&aColored &btext!";
String plain = LegacyTextHandler.stripFormatting(formatted);
plugin.getLogger().info("Plain text: " + plain); // Output: Plain text: Colored text!
```

**Example: Formatting Locations**
```java
import org.bukkit.Location;

public void showPlayerLocation(Player player) {
    String formattedLocation = LegacyTextHandler.formatLocation(player.getLocation());
    LegacyTextHandler.sendFormattedMessage(player, "&eYour location: " + formattedLocation);
}
```

**Output**:
```
Your location: (100.25, 64.00, 200.75)
```