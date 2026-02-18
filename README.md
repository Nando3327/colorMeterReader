# Compile

run: 

```
npm run build
npm pack
```

this create a dist file

To install in project use npm i ../color-meter-plugin, replace ../ to the route to find the project

If error with FastBLESdk in android by no lib found, copy .jar and aar from android/libs inside android[color-meter-plugin] libs, if libs do not exist, create it and sync gradle

# color-meter-plugin

plugin to connect to color meter sdk

## Install

```bash
npm i /Users/nando/IdeaProjects/colorMeterReader/color-meter-plugin-0.0.5.tgz 
npm install color-meter-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`reviewPermissions()`](#reviewpermissions)
* [`listPairedDevices()`](#listpaireddevices)
* [`connect(...)`](#connect)
* [`disconnect(...)`](#disconnect)
* [`valueDetected()`](#valuedetected)
* [`isReaderConnected()`](#isreaderconnected)
* [`getReaderCalibrationStatus()`](#getreadercalibrationstatus)
* [`calibrateWhite()`](#calibratewhite)
* [`calibrateBlack()`](#calibrateblack)
* [`initNueServiceBle()`](#initnueserviceble)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### reviewPermissions()

```typescript
reviewPermissions() => Promise<{ value: boolean; }>
```

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### listPairedDevices()

```typescript
listPairedDevices() => { devices: any[]; }
```

**Returns:** <code>{ devices: any[]; }</code>

--------------------


### connect(...)

```typescript
connect(options: { value: string; }) => Promise<{ value: boolean; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### disconnect(...)

```typescript
disconnect(options: { value: string; }) => Promise<{ value: boolean; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### valueDetected()

```typescript
valueDetected() => Promise<{ l: string; a: string; b: string; }>
```

**Returns:** <code>Promise&lt;{ l: string; a: string; b: string; }&gt;</code>

--------------------


### isReaderConnected()

```typescript
isReaderConnected() => Promise<{ value: boolean; }>
```

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### getReaderCalibrationStatus()

```typescript
getReaderCalibrationStatus() => Promise<{ black: boolean; white: boolean; }>
```

**Returns:** <code>Promise&lt;{ black: boolean; white: boolean; }&gt;</code>

--------------------


### calibrateWhite()

```typescript
calibrateWhite() => Promise<{ calibrated: boolean; }>
```

**Returns:** <code>Promise&lt;{ calibrated: boolean; }&gt;</code>

--------------------


### calibrateBlack()

```typescript
calibrateBlack() => Promise<{ calibrated: boolean; }>
```

**Returns:** <code>Promise&lt;{ calibrated: boolean; }&gt;</code>

--------------------


### initNueServiceBle()

```typescript
initNueServiceBle() => void
```

--------------------

</docgen-api>
