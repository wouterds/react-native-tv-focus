# @wouterds/react-native-tv-focus

A package to programatically focus views on tvOS & AndroidTV.

## Installation

```sh
# yarn
yarn add @wouterds/react-native-tv-focus

# npm
npm install @wouterds/react-native-tv-focus
```

## Usage

```js
import { findNodeHandle } from 'react-native';
import { focus } from '@wouterds/react-native-tv-focus';

// ...

// get tag by ref from a UI element
const tag = findNodeHandle(ref.current);

// programatically force focus on tag
focus(tag);
```
