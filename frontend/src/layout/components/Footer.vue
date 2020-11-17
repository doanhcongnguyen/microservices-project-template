<template>
  <div class="footer">
    <b>DZS © 2020</b> <span class="font10">version <b>{{ version }}</b></span>
  </div>
</template>

<script>
import { version } from '../../../package.json'

export default {
  data() {
    return {
      version: version
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/variables.scss";

.footer {
  background: $footerBg;
  height: 40px;
  text-align: right;
  padding-top: 10px;
  padding-right: 20px;
}

.font10 {
  font-size: 0.8em;
}
</style>
