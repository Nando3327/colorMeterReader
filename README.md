# color-meter-plugin

plugin to connect to color meter sdk

## Install

```bash
npm install color-meter-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`listPairedDevices()`](#listpaireddevices)
* [`connect(...)`](#connect)
* [`disconnect(...)`](#disconnect)
* [`valueDetected()`](#valuedetected)
* [`isReaderConnected()`](#isreaderconnected)
* [`getReaderCalibrationStatus()`](#getreadercalibrationstatus)

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
valueDetected() => Promise<{ value: any; }>
```

**Returns:** <code>Promise&lt;{ value: any; }&gt;</code>

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

</docgen-api>
