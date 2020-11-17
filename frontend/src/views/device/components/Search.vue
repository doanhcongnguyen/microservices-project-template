<template>
  <div>
    <el-form ref="form" :model="searchForm" label-position="right" label-width="25rem" size="mini">
      <el-form-item :label="$t('device.serial')">
        <el-input v-model="searchForm.serial" class="form-item" @keyup.enter.native="search" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">{{ $t('global.button.search') }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  props: {
    searchForm: {
      default: function() { return {} },
      type: Object,
      required: true
    }
  },
  methods: {
    search() {
      this.$emit('do-search')
    }
  }
}
</script>
