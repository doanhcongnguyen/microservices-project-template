<template>
  <div>
    <el-form ref="form" :model="searchForm" label-position="right" label-width="25rem" size="mini">
      <el-form-item :label="$t('settings.user.name')">
        <el-input v-model="searchForm.username" class="form-item" @keyup.enter.native="search" />
      </el-form-item>
      <el-form-item :label="$t('settings.user.fullName')">
        <el-input v-model="searchForm.fullName" class="form-item" @keyup.enter.native="search" />
      </el-form-item>
      <el-form-item :label="$t('settings.user.phoneNumber')">
        <el-input v-model="searchForm.telephone" class="form-item" @keyup.enter.native="search" />
      </el-form-item>
      <el-form-item :label="$t('settings.user.email')">
        <el-input v-model="searchForm.email" class="form-item" @keyup.enter.native="search" />
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
