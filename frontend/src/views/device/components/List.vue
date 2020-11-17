<template>
  <el-table
    v-loading="loading"
    :data="list"
    element-loading-text="Loading"
    border
    stripe
    fit
    highlight-current-row
    style="width: 100%"
    header-row-class-name="table-header"
    row-key="id"
    size="mini"
    @selection-change="handleSelect"
  >
    <el-table-column :reserve-selection="true" :min-width="60" type="selection" align="center" />
    <el-table-column label="#" :min-width="20" type="index" align="center" />
    <el-table-column :label="$t('device.serial')" :min-width="160" prop="serial" align="left" header-align="center" />
    <el-table-column :label="$t('device.modelName')" :min-width="100" prop="modelName" align="center" header-align="center" />
    <el-table-column :label="$t('device.softwareVersion')" :min-width="100" prop="softwareVersion" align="center" header-align="center" />
    <el-table-column :label="$t('device.status')" :min-width="100" prop="status" align="center" header-align="center" />
    <el-table-column :label="$t('global.label.action')" :min-width="90" align="center">
      <template slot-scope="scope">
        <el-tooltip :content="$t('global.button.sync')" placement="top" effect="light">
          <el-button type="text" icon="el-icon-refresh-right" @click="handleSync(scope.row)" />
        </el-tooltip>
        <el-tooltip :content="$t('global.button.update')" placement="top" effect="light">
          <el-button type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" />
        </el-tooltip>
        <el-tooltip :content="$t('global.button.delete')" placement="top" effect="light">
          <el-button type="text" icon="el-icon-delete" @click="handleDeleteItem(scope.row)" />
        </el-tooltip>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { doDelete, sync } from '@/api/device-management'
import { showSuccessMessage, createConfirmBox, showWarningMessage } from '@/utils/commons'

export default {
  props: {
    list: {
      default: () => { return [] },
      type: Array,
      required: true
    },
    loading: {
      default: () => { return false },
      type: Boolean
    }
  },
  methods: {
    async handleSync(model) {
      const response = await sync(model.serial)
      showWarningMessage(`${response.job}:  ${response.status}`)
    },
    handleSelect(model) {
      this.$emit('on-handle-select', model)
    },
    handleUpdate(model) {
      this.$emit('on-handle-update', model)
    },
    async handleDeleteItem(model) {
      await createConfirmBox(this, this.$i18n.t('device.deleteWarning') + ` ${model.serial}?`)
      this.continueDeleteItem(model)
    },
    async continueDeleteItem(model) {
      await doDelete(model.serial)
      this.showResultMessage()
    },
    showResultMessage() {
      showSuccessMessage()
      this.refresh()
    },
    refresh() {
      this.$emit('on-refresh')
    }
  }
}
</script>
