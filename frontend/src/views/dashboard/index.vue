<template>
  <div class="app-container">
    <el-card class="card" shadow="hover">
      <b>Dashboard</b>
      <div class="page-content">
        <el-form label-position="right" label-width="14rem" size="mini">
          <el-form-item :label="$t('global.label.name')">
            {{ name }}<br>
          </el-form-item>
          <el-form-item :label="$t('global.label.role')">
            <span v-for="role in roles" :key="role">{{ role }}, </span>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>

<style lang="scss" scoped>
</style>
